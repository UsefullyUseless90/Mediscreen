<template>
  <div class="pDetails">
  <h1>Patient's Details</h1>
  <table class="details">
    <thead>
        <tr>
          <th scope="col">Patient's id</th>
          <th scope="col">Name</th>
          <th scope="col">Firstname</th>
          <th scope="col">Date of birth</th>
          <th scope="col">Gender</th>
          <th scope="col">Postal address</th>
          <th scope="col">Phone number</th>
        </tr>
    </thead>
        <tbody v-for="(patient, index) in patients" :key="index">
          <tr>
            <td>{{ patient.idPatient }}</td>
            <td>{{ patient.name }}</td>
            <td>{{ patient.firstName }}</td>
            <td>{{ patient.birthDate }}</td>
            <td>{{ patient.gender }}</td>
            <td>{{ patient.postalAddress }}</td>
            <td>{{ patient.phoneNumber }}</td>
          </tr>
        </tbody>
  </table>
</div>
</template>
<script>
  import patientDataService from '../service/patientDataService'
  
  export default {
    name: "info-patient",
    data() {
      return {
        patients: [],
      }
    },
    mounted(){
    patientDataService.getAll().then((response) => {this.patients = {...response.data}}); 
      },
    }
  </script>
  
  
  <style>
  
  td {
      color: black;
    
    }
    .pDetails{
      margin-top: 10em;
      position: absolute;
    }
  th {
    background-color: rgb(50, 102, 151);
  }
  
  
  </style>