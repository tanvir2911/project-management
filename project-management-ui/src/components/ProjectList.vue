<template>
    <div>
      <h1>Project List</h1>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Intro</th>
            <th>Owner</th>
            <th>Status</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in projects" :key="project.id">
            <td>{{ project.name }}</td>
            <td>{{ project.intro }}</td>
            <td>{{ project.owner }}</td>
            <td>{{ project.status }}</td>
            <td>{{ project.startDateTime }}</td>
            <td>{{ project.endDateTime }}</td>
            <td>
              <button @click="editProject(project.id)">Edit</button>
              <button @click="deleteProject(project.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
      <button @click="showCreateProjectForm">Create New Project</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';
  
  const projects = ref([]);
  const router = useRouter();
  
  const fetchProjects = async () => {
    try {
      const response = await axios.get('/api/v1/projects');
      projects.value = response.data;
    } catch (error) {
      console.error('Failed to fetch projects:', error);
    }
  };
  
  const deleteProject = async (id) => {
    if (confirm('Are you sure you want to delete this project?')) {
      try {
        await axios.delete(`/api/v1/projects/${id}`);
        fetchProjects();
      } catch (error) {
        console.error('Failed to delete project:', error);
      }
    }
  };
  
  const editProject = (id) => {
    router.push(`/edit/${id}`);
  };
  
  const showCreateProjectForm = () => {
    router.push('/create');
  };
  
  onMounted(fetchProjects);
  </script>
  
  <style scoped>
  /* Add your styling here */
  </style>
  