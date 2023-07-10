import Table from 'react-bootstrap/Table';
import FormateCurrency from '../util/formatecurrency';
import FormateTime from '../util/formatetime';

function TableCnab({cnabsPaginated}) {
    const tableHeaders = [
        "Data", "Cpf", "Numero do cartao", "Hora", "Dono da loja",  "Nome da loja", "Valor", "Tipo", "Natureza"
    ]

    console.log(cnabsPaginated);

    return (
        <Table striped bordered hover>

            <thead>
                <tr>
                    <th>#</th>
                    {tableHeaders.map((headerName, index) => (
                        <th key={index}>{headerName}</th>
                    ))}
                </tr>
            </thead>

            <tbody>

                {cnabsPaginated?.content.map((cnab, index) => (
                    <tr key={index}>
                        <td>{index+1}</td>
                        <td>{cnab.date}</td>
                        <td>{cnab.cpf}</td>
                        <td>{cnab.cardNumber}</td>
                        <td>{FormateTime(cnab.hour)}</td>
                        <td>{cnab.onwnerStore}</td>
                        <td>{cnab.nameStore}</td>
                        <td>{FormateCurrency(cnab.value)}</td>
                        <td>{cnab.type.description}</td>
                        <td>{cnab.type.nature}</td>
                    </tr>    
                ))}
            </tbody>
        </Table>
    );
}

export default TableCnab;