<template>
   <div class="newPatient">
      <div class="card card-primary">
        <h1>New Patient</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="postt">
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
        <button @click="create()" class="btn btn-primary">Add Patient</button>
      </form>
  </div>
</template>

<script>
import patientServices from "../service/patientDataService"
export default {
  name: "add-item",
  data() {
    return {
      patientDAO: {
        name: "", 
        firstName: "",
        birthDate: "",
        gender: "",
        postalAddress: "",
        phoneNumber: ""
      },
    };
  },
  methods: {
    create() {
      patientServices.create(this.patientDAO)
        .then((response) => {
          this.patientDAO = response.data;
          this.submitted = true;
          this.$router.push('/patients')
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
};
</script>

<style scoped>
  .newPatient{
  position:absolute;
  margin-top: 5em;
  font-weight: bold;
  font-size: x-large;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  }
  
  .newPatient div {
    margin-bottom: 1em;
  }

  .newPatient h1 {
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
button{
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
    margin-left: 2%;
  }
</style>