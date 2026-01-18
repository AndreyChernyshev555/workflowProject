import { defineStore } from 'pinia'
import axios from 'axios'

export const useWorkflowStore = defineStore('counter', {
  state: () => {
    return { workflow: null }
  },
  actions: {
    async createWorkflow(workflowTypeId) {
      const result = await axios.post('http://localhost:8081/workflows', workflowTypeId)
      this.workflow = result.data
    },
  },
})
