<script setup>
import { onMounted, ref } from 'vue'
import WorkflowAPI from '@/api/WorkflowAPI.js'
import WorkflowStatuses from '@/domain/WorkflowStatuses.js'

const dialog = ref(false)
const workflowType = ref(null)
const workflows = ref([])
const options = ref([])
const workflowColumns = [
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
    field: (row) => WorkflowStatuses[row.status],
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

function onWorkflowDialogOpen() {
  dialog.value = true
}

function onWorkflowCreate() {
  WorkflowAPI.createWorkflow(workflowType.value)
  workflowType.value = null
}

const deleteRow = (item) => {
  workflows.value.splice(workflows.value.indexOf(item), 1)
  WorkflowAPI.deleteWorkflow(item)
}

onMounted(async () => {
  options.value = await WorkflowAPI.getAllWorkflowTemplates()
  workflows.value = await WorkflowAPI.getAllWorkflows()
  console.log(workflows.value)
})
</script>

<template>
  <div class="workflow-processes">
    <q-table :rows="workflows" :columns="workflowColumns" row-key="id">
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn icon="delete" color="primary" flat round @click="deleteRow(props.row)" />
        </q-td>
      </template>
    </q-table>
    <q-btn @click="onWorkflowDialogOpen">Создать процесс</q-btn>

    <q-dialog v-model="dialog" persistent>
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">Тип процесса</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-select v-model="workflowType" :options="options" option-label="name" />
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="Отмена" v-close-popup @click="() => (workflowType = null)" />
          <q-btn flat label="Добавить" v-close-popup @click="onWorkflowCreate()" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<style scoped>
.workflow-processes {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>
