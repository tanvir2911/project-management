import { createRouter, createWebHistory } from 'vue-router';
import ProjectList from '../components/ProjectList.vue';
import ProjectForm from '../components/ProjectForm.vue';

const routes = [
  { path: '/', component: ProjectList },
  { path: '/create', component: ProjectForm },
  { path: '/edit/:id', component: ProjectForm, props: true }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
