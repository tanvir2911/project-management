import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'; // Replace with your backend URL
axios.defaults.headers.common['Content-Type'] = 'application/json';

export default axios;
