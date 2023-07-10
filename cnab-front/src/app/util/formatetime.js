const FormateTime = (time) => {
    var timeString = time.toString();
    var hours = timeString.slice(0, 2);
    var minutes = timeString.slice(2, 4);
    var seconds = timeString.slice(4, 6);

    var formattedTime = hours + 'h:' +
        minutes + 'm:' +
        seconds + 's';

    return formattedTime;
}

export default FormateTime;