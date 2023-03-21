import PatientInfosVue from '@/views/PatientInfos.vue';
import PatientUpdateVue from '@/views/PatientUpdate.vue';
import RecapPatientVue from '@/views/RecapPatient.vue';
import { createRouter } from 'vue-router'

const router = createRouter({

  routes : [
    {
      path: '/',
      component:''
    },
    {
      path: '/patients',
      component: RecapPatientVue
    },
    {
      path: '/patientInformations',
      component: PatientInfosVue
    },
    {
      path: '/patientUpdate',
      component: PatientUpdateVue
    },
  ]
})