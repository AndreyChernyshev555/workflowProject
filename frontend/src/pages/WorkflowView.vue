<script setup>
import { ref } from 'vue'
import WorkflowTemplates from '@/components/workflow/WorkflowTemplates.vue'
import WorkflowProcesses from '@/components/workflow/WorkflowProcesses.vue'

const tab = ref(null)
</script>

<template>
  <q-page>
    <q-tabs v-model="tab" dense active-color="primary" indicator-color="primary" align="justify">
      <q-tab name="template" label="Шаблоны" />
      <q-tab name="processes" label="Процессы" />
    </q-tabs>
    <q-tab-panels v-model="tab" animated>
      <q-tab-panel name="template">
        <workflow-templates />
      </q-tab-panel>
      <q-tab-panel name="processes">
        <workflow-processes />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<style scoped></style>
