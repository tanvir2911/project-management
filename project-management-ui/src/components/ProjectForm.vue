<template>
    <div>
      <h1>{{ isEdit ? 'Edit Project' : 'Create Project' }}</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="name">Name:</label>
          <input v-model="project.name" type="text" id="name" required />
        </div>
        <div>
          <label for="intro">Intro:</label>
          <textarea v-model="project.intro" id="intro" required></textarea>
        </div>
        <div>
          <label for="owner">Owner:</label>
          <input v-model="project.owner" type="text" id="owner" required />
        </div>
        <div>
          <label for="status">Status:</label>
          <select v-model="project.status" id="status">
            <option value="PRE">Pre</option>
            <option value="START">Start</option>
            <option value="END">End</option>
          </select>
        </div>
        <div>
          <label for="startDateTime">Start Date:</label>
          <input v-model="project.startDateTime" type="date" id="startDateTime" />
        </div>
        <div>
          <label for="endDateTime">End Date:</label>
          <input v-model="project.endDateTime" type="date" id="endDateTime" />
        </div>
        <button type="submit">{{ isEdit ? 'Update' : 'Create' }}</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, watch } from 'vue';
  import axios from 'axios';
  import { useRoute, useRouter } from 'vue-router';
  
  const project = ref({
    name: '',
    intro: '',
    owner: '',
    status: 'PRE',
    startDateTime: '',
    endDateTime: ''
  });
  const isEdit = ref(false);
  const route = useRoute();
  const router = useRouter();
  
  const fetchProject = async (id) => {
    try {
      const response = await axios.get(`/api/v1/projects/${id}`);
      Object.assign(project.value, response.data);
      isEdit.value = true;
    } catch (error) {
      console.error('Failed to fetch project:', error);
    }
  };
  
  const submitForm = async () => {
    try {
      if (isEdit.value) {
        await axios.put(`/api/v1/projects/${project.value.id}`, project.value);
      } else {
        await axios.post('/api/v1/projects', project.value);
      }
      router.push('/');
    } catch (error) {
      console.error('Failed to save project:', error);
    }
  };
  
  watch(() => route.params.id, (newId) => {
    if (newId) {
      fetchProject(newId);
    }
  });
  
  onMounted(() => {
    if (route.params.id) {
      fetchProject(route.params.id);
    }
  });
  </script>
  
  <style scoped>
  /* Add your styling here */
  </style>
  