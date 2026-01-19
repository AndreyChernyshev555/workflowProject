<script setup>
import { useWorkflowStore } from '@/stores/WorkflowStore.js'
import { ref } from 'vue'

const workflowStore = useWorkflowStore()
const dialog = ref(false)
const workflowType = ref(null)
const options = [
  { id: 'BASE', label: 'Основной' },
  { id: 'BASE2', label: 'Логистика' },
]

function onWorkflowDialogOpen() {
  dialog.value = true
}

function onWorkflowCreate() {
  workflowStore.createWorkflow(workflowType.value.id)
  workflowType.value = null
}
</script>

<template>
  <div>
    <q-btn @click="onWorkflowDialogOpen">Создать шаблон процесса</q-btn>

    <q-dialog v-model="dialog" persistent>
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">Тип процесса</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-select v-model="workflowType" :options="options" />
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="Отмена" v-close-popup @click="() => (workflowType = null)" />
          <q-btn flat label="Добавить" v-close-popup @click="onWorkflowCreate()" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<style scoped></style>
