import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';

function OffCanvasHome({ children }) {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);


    return (
        <>
            <div style={{height: '50px'}}>
                    <Button variant="primary" onClick={handleShow} className="me-2">
                        Upload e Balanço
                    </Button>
                    <Offcanvas show={show} onHide={handleClose} placement='top'>
                        <Offcanvas.Body>
                            {children}
                        </Offcanvas.Body>
                    </Offcanvas>
            </div>

        </>
    );
}

export default OffCanvasHome;