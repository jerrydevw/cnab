'use client'

import styles from './page.module.css'

// Bootstrap CSS
import "bootstrap/dist/css/bootstrap.min.css";
// Bootstrap Bundle JS
import "bootstrap/dist/js/bootstrap.bundle.min";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';

import TableCnab from './component/table';
import PaginationCustom from './component/pagination';
import SearchTable from './component/search';
import SelectQuantityTable from './component/select';
import OffCanvasHome from './component/offcanvas';

import FormateCurrency from './util/formatecurrency';

import { useEffect, useState } from 'react';
import axios from 'axios';

export default function Home() {
  const [file, setFile] = useState();

  const [cnabs, setCnabs] = useState(null);

  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [storeName, setStoreName] = useState('');
  const [totalPages, setTotalPages] = useState(0);

  const [balance, setBalance] = useState(null);


  const handlePageChange = (newPage) => {
    setPage(newPage);
  }

  const handleFileChange = (e) => {
    if (e.target.files) {
      setFile(e.target.files[0]);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!file) {
      console.log("No file selected! ");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    axios.post('http://localhost:8080/cnab', formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        "Access-Control-Allow-Origin": "*"
      },
    })
    .then(function () {
      setFile();
    })
    .catch(function (error) {
      console.error(error);
    });

  };

  const getCnabs = async function getCnabs(page, size, storeName) {
    const params = new URLSearchParams();
    params.append('page', page);
    params.append('size', size);
    params.append('storeName', storeName);

    const result = await axios.get('http://localhost:8080/cnab', {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
      },
      params: params
    });

    return result.data;

  }

  const getCnabsBalance = async function getCnabs(storeName) {
    const params = new URLSearchParams();
    params.append('storeName', storeName);

    const result = await axios.get('http://localhost:8080/cnab/balance', {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
      },
      params: params
    });

    return result.data;

  }

  useEffect(() => {

  }, [file]);

  useEffect(() => {
    if (cnabs == null) {
      getCnabs(page, size, storeName)
      .then((result) => {
        setCnabs(result);
        setTotalPages(result.totalPages);
      })
    }
    
  }), [cnabs];

  useEffect(() => {
    if (cnabs != null) {
      getCnabs(page, size, storeName)
      .then((result) => {
        setCnabs(result);
        setTotalPages(result.totalPages);
      })

      if (storeName != null && storeName != "") {
        getCnabsBalance(storeName)
        .then((result) => {
          setBalance(result);
        })
      } else {
        setBalance(null);
      }
    }
  }, [page, size, storeName]);

  const contentCanvas = (handleFileChange, handleSubmit) => {
    return <>
    <Row>
        <Col xs={3}>
          <>
            <Form.Group controlId="formFile" className="mb-3">
              <Form.Control type="file" onChange={handleFileChange} />
            </Form.Group>
          </>
        </Col>

        <Col xs={2}>
          <Button variant="primary" type="submit" onClick={handleSubmit}>Upload</Button>
        </Col>

        {balance != null ? 
          <Col>
            <Form>
              <Form.Group as={Row} className="mb-4" controlId="formPlaintextEmail">
                <Form.Label column sm="2">
                  Empresa
                </Form.Label>
                <Col sm="10">
                  <Form.Control plaintext readOnly defaultValue={storeName} />
                </Col>
              </Form.Group>

              <Form.Group as={Row} className="mb-4" controlId="formPlaintextEmail">
                <Form.Label column sm="2">
                  Entradas
                </Form.Label>
                <Col sm="10">
                  <Form.Control plaintext readOnly defaultValue={FormateCurrency(balance.totalEntrance)} />
                </Col>
              </Form.Group>

              <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                <Form.Label column sm="2">
                  Saidas
                </Form.Label>
                <Col sm="10">
                  <Form.Control plaintext readOnly defaultValue={FormateCurrency(balance.totalExit)} />
                </Col>
              </Form.Group>

              <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                <Form.Label column sm="2">
                  Saldo
                </Form.Label>
                <Col sm="10">
                  <Form.Control plaintext readOnly defaultValue={FormateCurrency(balance.finalValue)} />
                </Col>
              </Form.Group>
            </Form>
          </Col> : <Col>
                      <h4>Pesquise uma empresa por nome para ver o saldo</h4>
                   </Col>}


    </Row>
    </>
  }

  return (

    <Container>
      <Row>
        <Col>
          <h1 className="text-center">Cnab</h1>
        </Col>
      </Row>
      <Row className="row mx-md-n5">
        <Col>
          <OffCanvasHome children={contentCanvas(handleFileChange, handleSubmit)}/>
        </Col>
      </Row>

      <Row>
        <Col>
          <SearchTable setStoreName={setStoreName}></SearchTable>
        </Col>
        <Col>
          <SelectQuantityTable setSize={setSize}></SelectQuantityTable>
        </Col>
      </Row>

      <Row>
        <TableCnab cnabsPaginated={cnabs}></TableCnab>
      </Row>

      <Row>
        <div className="d-flex justify-content-center">
          <PaginationCustom setPage={handlePageChange} currentPage={page} totalPages={totalPages}></PaginationCustom>
        </div>
      </Row>
    </Container>

  )
}