import Form from 'react-bootstrap/Form';

function handleSelect(event, setSize) {
    setSize(event.target.value);
}

function SelectQuantityTable({setSize}) {    
    return (
        <Form.Select onChange={(event) => handleSelect(event, setSize)} aria-label="Default select example">
            <option value="10" >10</option>
            <option value="30" >30</option>
            <option value="50" >50</option>
            <option value="100" >100</option>
        </Form.Select>
    );
}

export default SelectQuantityTable;