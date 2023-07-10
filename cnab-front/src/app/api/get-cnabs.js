import axios from 'axios';

const GetCnabs = async (params) => {
    const result = await axios.get('http://localhost:8080/cnab', {
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        },
        params: params
    });

    return result.data;
}

export default GetCnabs;