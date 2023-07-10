import { useEffect, useState } from 'react';
import Form from 'react-bootstrap/Form';

function SearchTable({ setStoreName }) {
    const [searchTerm, setSearchTerm] = useState('');

    const handleSearchTerm = (value) => {
        setSearchTerm(value);
    };

    useEffect(() => {
        const delayDebounceFn = setTimeout(() => {
            setStoreName(searchTerm);
        }, 600);

        return () => {
            clearTimeout(delayDebounceFn);
        };
    }, [searchTerm, setStoreName]);

    return (
        <>
            <Form.Control
                type="text"
                id="searchCnab"
                aria-describedby="HelpBlock"
                onChange={(e) => handleSearchTerm(e.target.value)}
            />
            <Form.Text id="HelpBlock" muted>
                Informe o nome da loja
            </Form.Text>
        </>
    );
}

export default SearchTable;
