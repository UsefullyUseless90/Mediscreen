<template>
  <div class="container">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Update patient's informations</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="put">
        <div class="form-group">
          <label for="Nom"> idPatient </label>
          <input type="text" class="form-control" id="idPatient" v-model="patientDAO.idPatient"/>
        </div>
        <div class="form-group">
          <label for="Nom"> Name </label>
          <input type="text" class="form-control" id="name" v-model="patientDAO.name"/>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Prenom"> First Name </label>
          <input type="text" class="form-control" id="firstName" v-model="patientDAO.firstName" />
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="AdresseEmail" > Date of birth </label>
          <input type="text" class="form-control" id="birthDate" v-model="patientDAO.birthDate"/>
        </div>
        <div class="form-group">
          <label for="AdresseEmail" > Gender </label>
          <input type="text" class="form-control" id="gender" v-model="patientDAO.gender"/>
        </div>
        <div class="form-group">
          <label for="AdresseEmail" > Postal Address </label>
          <input type="text" class="form-control" id="postalAddress" v-model="patientDAO.postalAddress"/>
        </div>
        <div class="form-group">
          <label for="AdresseEmail" > Phone Number </label>
          <input type="text" class="form-control" id="phoneNumber" v-model="patientDAO.phoneNumber"/>
        </div>
        <button @click="update()" class="btn btn-primary">Validation Changement Informations</button>
      </form>
  </div>
      
</template>

<script>
import patientDataService from "../../service/patientDataService";
export default {
  name: "edition-patient",
  data() {
    return {
      patientDAO:{
        idPatient: 0,
        name: "", 
        firstName: "",
        birthDate: "",
        gender: "",
        postalAddress: "",
        phoneNumber: ""
      }
    }
  },

  methods: {
    update() {
      patientDataService.update(this.patientDAO)
      .then((response) =>{
        this.patient = {...response.data}
      },
      )
    },
  }
}
</script>
<style scoped>
  .container{
  margin-top: 20%;
  font-weight: bold;
  font-size: x-large;
  display: block;
  }
  label{
    font-weight: bold;
    font-size: x-large;
    width: 300px;
  }
  input{
    width: 300px;
  }
  .nav{
    margin-left: 75%;
  }
  button{
    background-color: blue;
    color: #fff;
    height: 50px;
    border: 1px blue;
    border-radius: 20px / 20px;
    font-weight: bold;
    font-size: x-large;
    margin-top: 25px;
    width: 300px;
    height: 80px;
    margin-left: 2%;
  }
  </style>