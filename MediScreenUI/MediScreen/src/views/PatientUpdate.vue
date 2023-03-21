<template>
  <div class="patientPage">
      <div class="card card-primary">
        <h1>Patient's informations</h1>
      </div>
      <div class="card-body"></div>

      <form action="app.js" method="put">
        <div class="form-group">
          <label for="LastName"> Last name </label>
          <input type="text" class="form-control" id="name" v-model="patientDAO.name"/>
        </div>
        <div class="form-group">
          <label for="FirstName"> First name </label>
          <input type="text" class="form-control" id="firstName" v-model="patientDAO.firstName" />
        </div>
        <div class="form-group">
          <label for="BirthDate" > Date of birth </label>
          <input type="text" class="form-control" id="birthDate" v-model="patientDAO.birthDate"/>
        </div>
        <div class="form-group">
          <label for="Gender" > Gender </label>
          <input type="text" class="form-control" id="gender" v-model="patientDAO.gender"/>
        </div>
        <div class="form-group">
          <label for="PostalAddress" > Postal Address </label>
          <input type="text" class="form-control" id="postalAddress" v-model="patientDAO.postalAddress"/>
        </div>
        <div class="form-group">
          <label for="PhoneNumber" > Phone Number </label>
          <input type="text" class="form-control" id="phoneNumber" v-model="patientDAO.phoneNumber"/>
        </div>
        <div class="containsB">
          <router-link to="/patientInformations" class="nav-link"><button class="button11" @click="update()">Update Informations</button></router-link>
        </div>
      </form>
  </div>
      
</template>

<script>

import patientDataService from "../service/patientDataService";

export default {
  name: "edition-patient",
  data() {
    return {
      idPatient: 0,
      patientDAO:{
        name: "", 
        firstName: "",
        birthDate: "",
        gender: "",
        postalAddress: "",
        phoneNumber: ""
      }
    }
  },
 
  mounted(){
    console.log(this.$route.params.id);
    patientDataService.getPatientId(this.$route.params.id).then((response) => {this.patientDAO = {...response.data};
      })
  },

  methods: {
    update() {
      patientDataService.update(this.patientDAO).then((response) => {console.log(response.status);
      })
      .catch((error) => console.error({error})); 
      }
    },
    computed :{
    lookForId(){
      patientDataService.getPatientId(this.idPatient).then((response) => {this.patientDAO = {...response.data};
      })
    }
  },
}

</script>
<style scoped>
  .patientPage{
  position:absolute;
  margin-top: 5em;
  font-weight: bold;
  font-size: x-large;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  }
  .searchButton{
    margin-left: 24em;
    font-weight: bolder;
    position: absolute;
    background-color: rgb(50, 102, 151);
    color: #fff;
    border: 1px rgb(50, 102, 151);;
    border-radius: 20px / 20px;
    margin-top: 15em;
    height: 2.5em;
    width: 300px;
  }

  .patientPage div {
    margin-bottom: 1em;
  }

  .patientPage h1 {
    text-transform: uppercase;
  }
  label{
    font-weight: bold;
    font-size: x-large;
    width: 300px;
  }

  input{
    width: 300px;
    display: flex;
    margin-top: 0.5em;
    height: 2em;
  }
  .button11{
    background-color: rgb(50, 102, 151);
    color: #fff;
    height: 50px;
    border: 1px rgb(50, 102, 151);;
    border-radius: 20px / 20px;
    font-weight: bold;
    font-size: x-large;
    margin-top: 25px;
    width: 300px;
    height: 80px;
  }
  </style>