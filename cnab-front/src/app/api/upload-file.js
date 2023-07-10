import axios from 'axios';

const UploadFile = async (formData) => {
    const promisseResult = await axios.post('http://localhost:8080/cnab', formData, {
        headers: {
            "Content-Type": "multipart/form-data",
            "Access-Control-Allow-Origin": "*"
        },
    })
        .then(function () {
            console.log("File uploaded successfully! gettingData");
        })
        .catch(function (error) {
            console.error(error);
        });

    return promisseResult;

}

export default UploadFile;