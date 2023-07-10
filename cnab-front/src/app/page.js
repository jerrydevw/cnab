'use client'

// Bootstrap CSS
import "bootstrap/dist/css/bootstrap.min.css";
// Bootstrap Bundle JS
import "bootstrap/dist/js/bootstrap.bundle.min";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import TableCnab from './component/table';
import PaginationCustom from './component/pagination';
import SearchTable from './component/search';
import SelectQuantityTable from './component/select';

import FormateCurrency from './util/formatecurrency';

import UploadFile from './api/upload-file';
import GetCnabs from './api/get-cnabs';

import { useEffect, useState } from 'react';

import GetCnabsBalance from './api/get-cnabs-balance';

export default function Home() {
  const [file, setFile] = useState();

  const [cnabs, setCnabs] = useState(null);

  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [storeName, setStoreName] = useState('');

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

    UploadFile(formData)
    .then(() => {
      setTimeout(() => {
        console.log("get data in timeout");
        if (cnabs == null || cnabs.empty) {
          getCnabs(page, size, storeName)
            .then((cnabsResult) => {
              setCnabs(cnabsResult);
            })
          }
      }, 500);

    })

  };

  const getCnabs = async(page, size, storeName) => {
    const params = new URLSearchParams();
    params.append('page', page);
    params.append('size', size);
    params.append('storeName', storeName);

    return await GetCnabs(params);
  }

  const getCnabsBalance = async (storeName) => {
    const params = new URLSearchParams();
    params.append('storeName', storeName);

    return await GetCnabsBalance(params);
  }

  useEffect(() => {
    if (cnabs == null) {
      getCnabs(page, size, storeName)
      .then((cnabsResult) => {
        setCnabs(cnabsResult);
      })
    }
    
  }), [cnabs];

  useEffect(() => {
    if (cnabs != null) {
      if(storeName != null && storeName != "") {
        setPage(0);
      }
      getCnabs(page, size, storeName)
      .then((cnabsResult) => {
        setCnabs(cnabsResult);
      });


      if (storeName != null && storeName != "") {
        getCnabsBalance(storeName)
        .then((balanceResult) => {
          setBalance(balanceResult);
        })

      } else {
        setBalance(null);
      }
    }
  }, [page, size, storeName]);

  return (

    <Container>
      <Row>
        <Col>
          <h1 className="text-center">Cnab</h1>
        </Col>
      </Row>
      <Row className="row mx-md-n5">
        <Col>
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
                <p className="text-center">Entradas: {FormateCurrency(balance.totalEntrance)}</p>
                <p className="text-center">Saidas: {FormateCurrency(balance.totalExit)}</p>
                <p className="text-center">Saldo: {FormateCurrency(balance.finalValue)}</p>
              </Col> : null }
          </Row>
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
          <PaginationCustom setPage={handlePageChange} currentPage={page} totalPages={cnabs?.totalPages ? cnabs.totalPages : 0}></PaginationCustom>
        </div>
      </Row>
    </Container>

  )
}