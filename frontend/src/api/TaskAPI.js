import axios from 'axios'

const baseUrl = 'http://localhost:8082/tasks'
export default class TaskAPI {
  static async getAllTasks() {
    const result = await axios.get(baseUrl)
    return result.data
  }
}
