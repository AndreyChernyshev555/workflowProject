<script setup>
import { onMounted, ref } from 'vue'
import WorkflowAPI from '@/api/WorkflowAPI.js'

const dialog = ref(false)
const rowDialog = ref(false)
const editedItem = ref({})
const editedIndex = ref(-1)

const editRow = (item) => {
  editedIndex.value = templates.value.indexOf(item)
  editedItem.value = Object.assign({}, item)
  rowDialog.value = true
}

const deleteRow = (item) => {
  templates.value.splice(templates.value.indexOf(item), 1)
  WorkflowAPI.deleteWorkflowTemplate(item)
}

const closeDialog = () => {
  rowDialog.value = false
  setTimeout(() => {
    editedItem.value = {}
    editedIndex.value = -1
  }, 300)
}

const saveChanges = () => {
  if (editedIndex.value > -1) {
    Object.assign(templates.value[editedIndex.value], editedItem.value)
    WorkflowAPI.updateWorkflowTemplate(editedItem.value)
  }
  closeDialog()
}

const workflowTemplate = ref({})
const templates = ref([])
const columns = [
  {
    name: 'name',
    required: true,
    label: 'Название',
    align: 'left',
    field: (row) => row.name,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: 'description',
    required: true,
    label: 'Описание',
    align: 'left',
    field: (row) => row.description,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: 'actions',
    required: true,
    label: 'Действия',
    field: 'actions',
    align: 'left',
  },
]

const stepColumns = [
  {
    name: 'stepKey',
    label: 'Идентификатор',
    align: 'left',
    field: 'stepKey',
  },
  {
    name: 'name',
    label: 'Название',
    align: 'left',
    field: 'name',
  },
  {
    name: 'stepIndex',
    label: 'Номер',
    align: 'left',
    field: 'stepIndex',
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
  WorkflowAPI.createWorkflowTemplate(workflowTemplate.value)
  workflowTemplate.value = null
}

onMounted(async () => {
  templates.value = await WorkflowAPI.getAllWorkflowTemplates()
})
</script>

<template>
  <div>
    <q-table title="Все доступные" :rows="templates" :columns="columns" row-key="name">
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn icon="edit" color="primary" flat round @click="editRow(props.row)" />
          <q-btn icon="delete" color="primary" flat round @click="deleteRow(props.row)" />
        </q-td>
      </template>
    </q-table>
    <q-dialog v-model="rowDialog" persistent full-width>
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">Редактирование</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input v-model="editedItem.name" label="Name" autofocus />
          <q-input v-model="editedItem.description" label="Описание" />
          <q-table title="Шаги" :rows="editedItem.steps" :columns="stepColumns" row-key="id">
            <template v-slot:body-cell-name="props">
              <q-td :props="props">
                <q-input v-model="props.row.name" label="Название" />
              </q-td>
            </template>
            <template v-slot:body-cell-stepIndex="props">
              <q-td :props="props">
                <q-input v-model="props.row.stepIndex" type="number" label="Номер шага" />
              </q-td>
            </template>
            <template v-slot:body-cell-stepKey="props">
              <q-td :props="props">
                <q-input v-model="props.row.stepKey" label="Ключ" />
              </q-td>
            </template>
          </q-table>
          <q-btn
            icon="add"
            color="primary"
            flat
            @click="
              () =>
                (editedItem.steps = [
                  {
                    stepKey: '',
                    name: '',
                    stepIndex: editedItem.steps && editedItem.steps.length,
                  },
                ])
            "
          >
            Добавить шаг
          </q-btn>
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="Cancel" @click="closeDialog" />
          <q-btn flat label="Save" @click="saveChanges" />
        </q-card-actions>
      </q-card>
    </q-dialog>

    <q-btn @click="onWorkflowDialogOpen">Создать шаблон процесса</q-btn>

    <q-dialog v-model="dialog" persistent>
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">Тип процесса</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input v-model="workflowTemplate.name" label="Название" />
        </q-card-section>
        <q-card-section class="q-pt-none">
          <q-input v-model="workflowTemplate.description" label="Описание" />
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="Отмена" v-close-popup @click="() => (workflowTemplate = {})" />
          <q-btn flat label="Добавить" v-close-popup @click="onWorkflowCreate()" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<style scoped></style>
