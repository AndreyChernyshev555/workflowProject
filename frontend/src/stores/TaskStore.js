import { defineStore } from 'pinia'
import axios from 'axios'

export const useTaskStore = defineStore('counter', {
  state: () => {
    return { task: null }
  },
  actions: {
    async getTask(taskId) {
      const result = await axios.post('http://localhost:8082/tasks', taskId)
      this.task = result.data
    },
  },
})
