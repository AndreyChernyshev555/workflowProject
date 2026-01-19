import axios from 'axios'

const defBaseUrl = 'http://localhost:8081/api/definitions'
const workflowBaseUrl = 'http://localhost:8081/workflows'
export default class WorkflowAPI {
  static async getAllWorkflowTemplates() {
    const result = await axios.get(defBaseUrl)
    return result.data
  }
  static async updateWorkflowTemplate(workflowTemplate) {
    const result = await axios.put(`${defBaseUrl}/${workflowTemplate.id}`, workflowTemplate)
    return result.data
  }
  static async deleteWorkflowTemplate(workflowTemplate) {
    const result = await axios.delete(`${defBaseUrl}/${workflowTemplate.id}`)
    return result.data
  }
  static async createWorkflowTemplate(workflowTemplate) {
    const result = await axios.post(defBaseUrl, workflowTemplate)
    return result.data
  }
  static async createWorkflow(workflowTypeId) {
    const result = await axios.post(workflowBaseUrl, workflowTypeId)
    return result.data
  }
}
