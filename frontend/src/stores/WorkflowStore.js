import { defineStore } from 'pinia'
import axios from 'axios'
import WorkflowAPI from '@/api/WorkflowAPI.js'

export const useWorkflowStore = defineStore('workflow', {
  state: () => {
    return { workflow: null }
  },
  actions: {
    async createWorkflow(workflowTypeId) {
      const result = WorkflowAPI.createWorkflow(workflowTypeId);
      this.workflow = result.data
    },
  },
})
