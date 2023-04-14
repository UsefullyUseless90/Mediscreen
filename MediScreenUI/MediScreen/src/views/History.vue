<template>
  <div class="intel">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Patient's informations</h1>
      </div>
      <form action="app.js" method="put">
        <div class="form-group">
          <label for="Nom"> Name </label>
          <p>{{ patientDAO.name}}</p>
        </div>
      </form>
      <form>
        <div class="formAdd">
          <label for="Prenom"> First Name </label>
          <p>{{ patientDAO.firstName}}</p>
        </div>
      </form>
        <form>
          <div class="form-group">
            <label for="birthDate" > Date of birth </label>
              <p>{{patientDAO.birthDate}} ({{ report.age }} years old)</p>  
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
  <table class="head">
    <thead>
        <tr>
          <th scope="col">Date</th>
          <th scope="col">Observation</th>
        </tr>
    </thead>
      <tbody class="corpse" v-for="(history, index) in histories" :key="index">
          <tr>
            <td>{{ history.dateOfInterview }}</td>
            <td>{{ history.commentary }}</td>
            <td><router-link :to="{ name: 'updateHistory', params: { id: history.historyId }}"><button class="ub">Update history</button></router-link></td>
          </tr>
        </tbody>
  </table>
  <div class="add">
    <h1 for="assessement">Patient assessement:</h1>
      <p>{{ report.assessment }}</p>
     

      <h2>Commentary for today's interview</h2>
          <input class="inter" type="text" id="commentary" name="commentary" v-model="historyDTO.practitionerSNotesRecommandation"/>
          <button class="bc" @click="create()">Add commentary</button>
  </div>  
</div>
      
</template>
<script>

import historyDataService from "../service/historyDataService";
import patientDataService from "../service/patientDataService";
import assessDataService from "../service/AssessementDataService"

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
        historyDTO:{
          patientId:0,
          patientName: "", 
          practitionerSNotesRecommandation:"",
        },
        report:{
          age:0,
          assessment:"",
          patient:{
            idPatient: 0,
            name: "", 
            firstName: "",
            birthDate: "",
            gender: "",
            postalAddress: "",
            phoneNumber: ""
          },
        },
        histories:[]
      }
    },
    mounted(){
      console.log(this.$route.params.id);
        patientDataService.getPatientId(this.$route.params.id).then((response) => {this.patientDAO = {...response.data};
      })
        console.log(this.$route.params.id);
        historyDataService.getHistoriesId(this.$route.params.id).then((response) => {this.histories = {...response.data};
      })
      console.log(this.$route.params.id);
      assessDataService.getAssessById(this.$route.params.id).then((response) => {this.report = {...response.data};
     })
    },
    methods: {
    create() {
        this.$data.historyDTO.patientId = this.$data.patientDAO.idPatient
        this.$data.historyDTO.patientName = this.$data.patientDAO.name
        historyDataService.create(this.$data.historyDTO).then((response) => {
          this.historyToSend = response.data;
          this.submitted = true;
          window.location.reload();
      })
      .catch((error) => console.error({error})); 
      },
   
    },
  }
  </script>
  <style scoped>
.intel{
  position: absolute;
  margin-top: 15em;
  margin-left: -129em;
}

.intel h1{
  color: rgb(50, 102, 151);
  font-weight: bold;
  font-size: x-large;
}
.intel label{
  font-weight: bold;
  font-size: x-large;
  margin-top: 2em;
  display: flex;
}
.head{
  position: absolute;
  margin-left: 27.25em;
  margin-top: -22em;
  width: 40em;
}
.head thead{
  background-color: rgb(50, 102, 151);
  color: white;
  font-weight: bolder;
}
.add{
  position: absolute;
    margin-left: 23em;
    margin-top: -45em;
}
.inter{
  height: 30em;
  width: 30em;
}
.bc{
    margin-left: -26em;
    font-weight: bolder;
    position: absolute;
    background-color: rgb(50, 102, 151);
    color: #fff;
    border: 1px rgb(50, 102, 151);
    border-radius: 20px / 20px;
    margin-top: 33em;
    height: 2.5em;
    width: 300px;
}
.ub{
    margin-left: 1em;
    font-weight: bolder;
    position: absolute;
    background-color: rgb(50, 102, 151);
    color: #fff;
    border: 1px rgb(50, 102, 151);
    border-radius: 20px / 20px;
    margin-top: -1em;
    height: 2.5em;
    width: 156px;
}
  </style>