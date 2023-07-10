import axios from 'axios';

const GetCnabsBalance = async (params) => {
    const result = await axios.get('http://localhost:8080/cnab/balance', {
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        },
        params: params
    });

    return result.data;
}

export default GetCnabsBalance;