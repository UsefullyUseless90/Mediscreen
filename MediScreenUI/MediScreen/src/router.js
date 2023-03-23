import { createRouter, createWebHistory } from "vue-router";
import PatientInfosVue from './views/PatientDetailsTable.vue';
import PatientUpdateVue from '@/views/PatientUpdate.vue';
import RecapPatientVue from '@/views/RecapPatient.vue';
import AddPatientVue from './views/AddPatient.vue';
import HistoryVue from './views/History.vue';
import HistoryUpdateVue from './views/HistoryUpdate.vue'
import Home from '@/views/Home.vue';

const routes =[
  { path: '/', name: 'home', component: Home },
  { path: '/patients', name: 'patient', component:RecapPatientVue},
  { path: '/patientInformations', name: 'patientInfos', component:PatientInfosVue},
  { path: '/patientUpdate/:id', name: 'update', component:PatientUpdateVue },
  { path: '/addNewpatient', name: 'add', component:AddPatientVue },
  { path: '/patientsHistory/:id', name: 'history', component:HistoryVue },
  { path: '/patientsHistory/update/:id', name: 'updateHistory', component: HistoryUpdateVue}

]

const history = createWebHistory();

const router = createRouter({history,routes})

export default router