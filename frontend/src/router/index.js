import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../pages/WorkflowView.vue'),
    },
    {
      path: '/task',
      name: 'task',
      component: () => import('../pages/TaskView.vue'),
    },
  ],
})

export default router
