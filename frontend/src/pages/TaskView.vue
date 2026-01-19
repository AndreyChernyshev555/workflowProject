<script setup>
import { onMounted, ref } from 'vue'
import TaskAPI from '@/api/TaskAPI.js'
import TaskStatuses from '@/domain/TaskStatuses.js'

const tasks = ref([])
const taskColumns = [
  {
    name: 'label',
    label: 'Название',
    align: 'left',
    field: 'label',
  },
  {
    name: 'status',
    label: 'Статус',
    align: 'left',
    field: (row) => TaskStatuses[row.status],
    sortable: true,
  },
  {
    name: 'actions',
    label: 'Действия',
    align: 'center',
    field: 'actions',
    sortable: false,
  },
]

onMounted(async () => {
  tasks.value = await TaskAPI.getAllTasks()
  console.log(tasks)
})
</script>

<template>
  <q-page class="page">
    <q-table class="full-width q-mx-lg" :rows="tasks" :columns="taskColumns" row-key="id" />
  </q-page>
</template>

<style scoped>
.task-card {
  min-width: 30vw;
  min-height: 20vh;
}
</style>
