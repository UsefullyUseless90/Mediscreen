<template>
  <div class="table">
    <div>
    <div class="infos">
          <br/>
       <strong><p>Name</p></strong>
        <input
          type="text"
          id="name"
          name="name"
          v-model="patient.name"
        />
        <br/>
      <strong><p>Firstname</p></strong> 
        <input
          type="text"
          id="firstName"
          name="firstName"
          v-model="patient.firstName"/>
          <button  @click="lookOutForPatient()" class="sb">Search</button>
          <button  @click="clearFilter()" class="sb">Clear</button>
    </div>
    <table class="center">
      <thead>
        <tr>
          <th scope="col">Patient's id</th>
          <th scope="col">Name</th>
          <th scope="col">Firstname</th>
          <th scope="col"></th>
        </tr>
      </thead>
    </table>
  </div>
    <table>
      <tbody v-for="(patient, index) in patients" :key="index">
        <tr>
          <td>{{ patient.idPatient }}</td>
          <td>{{ patient.name}}</td>
          <td>{{ patient.firstName }}</td>
          <td><router-link :to="{ name: 'update', params: { id: patient.idPatient }}"><button class="btn btn-primary">Update</button></router-link></td>
        </tr>
      </tbody>
    </table>
    
  </div>
</template>
<script>

import patientDataService from "../../service/patientDataService";


export default {
  name: "patients-item",
  data() {
    return {
      patient:{
        idPatient: 0,
        name: "", 
        firstName: "",
      },
      patients: [],
      patientsComplet: []
    };
  },
  
  mounted(){
    patientDataService.getAll().then((response)=>{
      this.patients = {...response.data};
      this.patientsComplet = {...response.data};
    });
  },
  methods:{
    lookOutForPatient(){
    patientDataService.getPatient(this.patient.name, this.patient.firstName).then((response) => {this.patients = {...response.data}}); 
      },
      clearFilter(){
        this.patients = this.patientsComplet;
      }
  }
}
</script>
<style>

    .infos p {
      font-weight: bold;
      font-size: xx-large;
      color: rgb(50, 102, 151);
      margin-top: 0.5em;
    }

    table {
      text-align: center;
      font-weight: bold;
      font-size: xx-large;
      height: 150px;
      width: 1550px;
    }
    
    td {
      color: black;
    
    }
    .btn{
    background-color: rgb(50, 102, 151);
    color: #fff;
    height: 50px;
    border: 1px rgb(50, 102, 151);;
    border-radius: 20px / 20px;
    font-weight: bold;
    font-size: x-large;
    margin-top: 5em;
    width: 300px;
    height: 60px;
    margin-left: 55em;
  }
  .sb{
    font-weight: bold;
    font-size: x-large;
    background-color: rgb(50, 102, 151);
    color: #fff;
    border: 1px rgb(50, 102, 151);;
    border-radius: 20px / 20px;
    margin-top: 0.5em;
    height: 2.3em;
    width: 300px;
  }
    </style>