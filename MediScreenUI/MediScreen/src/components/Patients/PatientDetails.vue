<template>
  <div class="container">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Patient's informations</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="put">
        <div class="form-group">
          <label for="Nom"> Name </label>
          <td>{{ patientDAO.name}}</td>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Prenom"> First Name </label>
          <td>{{ patientDAO.firstName}}</td>
        </div>
      </form>
        <form>
          <div class="form-group">
            <label for="birthDate" > Date of birth </label>
              <p>{{patientDAO.birthDate}}</p>
          </div>
          <div class="form-group">
            <label for="gender" > Gender </label>
            <p>{{patientDAO.gender}}</p>
          </div>
          <div class="form-group">
            <label for="AdresseEmail" > Postal Address </label>
            <p>{{patientDAO.postalAddress}}</p>
          </div>
          <div class="form-group">
            <label for="phoneNumber" > Phone Number </label>
            <p>{{patientDAO.phoneNumber}}</p>
          </div>
      </form>
  </div>
      
</template>

<script>
  export default {
    name: "info-patient",
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
        },
        patients:[]
      }
    },
    mounted(){
    patientDataService.getAll().then((response)=>{
      this.patients = {...response.data};
    });
  },
  }
    </script>