<script setup>
import { RouterView } from 'vue-router'
import router from '@/router/index.js'
</script>

<template>
  <q-layout view="hHh lpR fFf" class="main-window">
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
          </q-avatar>
          Workflow Project
          <q-btn-group flat>
            <q-btn flat unelevated @click="() => router.push('/')">Workflow</q-btn>
            <q-btn flat unelevated @click="() => router.push('/task')">Task</q-btn>
          </q-btn-group>
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container class="main-container">
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<style>
.page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 1em;
}
</style>
