import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './axios'; // Import Axios configuration

createApp(App)
  .use(router)
  .mount('#app');
