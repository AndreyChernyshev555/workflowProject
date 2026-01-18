<script setup>
import { useTaskStore } from '@/stores/TaskStore.js'
import { ref } from 'vue'

const taskStore = useTaskStore()
const task = ref({
  label: 'mock',
  status: { id: 'BASE2', label: 'Логистика' },
})
const taskStatuses = [
  { id: 'BASE', label: 'Основной' },
  { id: 'BASE2', label: 'Логистика' },
]
</script>

<template>
  <q-page class="page">
    <q-card class="task-card">
      <q-card-section>
        {{ task.label }}
      </q-card-section>
      <q-card-section>
        <q-select v-model="task.status" :options="taskStatuses" label="Статус" />
      </q-card-section>
    </q-card>
  </q-page>
</template>

<style scoped>
.task-card {
  min-width: 30vw;
  min-height: 20vh;
}
</style>
