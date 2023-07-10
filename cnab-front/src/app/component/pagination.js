import { useEffect, useState } from 'react';
import Pagination from 'react-bootstrap/Pagination';

function PaginationCustom({ setPage, currentPage, totalPages }) {
    const quantity = 3;
    const [pageNumbers, setPageNumbers] = useState([]);

    useEffect(() => {
        const generatePageNumbers = () => {
            const numbers = [];
            for (let i = 1; i <= totalPages; i++) {
                numbers.push(i);
            }
            return numbers;
        };

        setPageNumbers(generatePageNumbers());
    }, [totalPages]);

    const goToPage = (page) => {
        setPage(page);
    };

    const goToFirstPage = () => {
        goToPage(0);
    };

    const goToPrevPage = () => {
        if (currentPage > 0) {
            goToPage(currentPage - 1);
        }
    };

    const goToNextPage = () => {
        if (currentPage < totalPages - 1) {
            goToPage(currentPage + 1);
        }
    };

    const goToLastPage = () => {
        goToPage(totalPages - 1);
    };

    const renderPageNumbers = () => {
        let start = currentPage - Math.floor(quantity / 2);
        if (start < 0) {
            start = 0;
        }
        let end = start + quantity;
        if (end > totalPages) {
            end = totalPages;
            start = end - quantity;
            if (start < 0) {
                start = 0;
            }
        }

        const pageRange = pageNumbers.slice(start, end);

        return pageRange.map((number) => (
            <Pagination.Item
                key={number}
                active={number - 1 === currentPage}
                onClick={() => goToPage(number - 1)}
            >
                {number}
            </Pagination.Item>
        ));
    };

    return (
        <Pagination>
            <Pagination.First onClick={goToFirstPage} disabled={currentPage === 0} />
            <Pagination.Prev onClick={goToPrevPage} disabled={currentPage === 0} />

            {renderPageNumbers()}

            <Pagination.Next onClick={goToNextPage} disabled={currentPage === totalPages} />
            <Pagination.Last onClick={goToLastPage} disabled={currentPage === totalPages} />
        </Pagination>
    );
}

export default PaginationCustom;
