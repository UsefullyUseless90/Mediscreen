<template>
  <div class="intel">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Patient's informations</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="put">
        <div class="form-group">
          <label for="Nom"> Name </label>
          <p>{{ patientDAO.name}}</p>
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Prenom"> First Name </label>
          <p>{{ patientDAO.firstName}}</p>
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
            <td><router-link :to="{ name: 'updateHistory', params: { id: patientDAO.idPatient }}"><button class="ub" @click="update()">Update history</button></router-link></td>
          </tr>
        </tbody>
  </table>
  <div class="add">
      <h2>Commentary for today's interview</h2>
      <input type="text" id="date" name="date" v-model="history.dateOfInterview" />
      <input class="inter" type="text" id="commentary" name="commentary" v-model="history.commentary"/>
      <button class="bc" @click="create()">Add commentary</button>
  </div>  
</div>
      
</template>
<script>

import historyDataService from "../service/historyDataService";
import patientDataService from "../service/patientDataService";

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
        history:{
          idPatient: this.$route.params.id,
          name: "", 
          firstName: "",
          commentary:"",
          dateOfInterview:""
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
    },
    methods: {
    create() {
        historyDataService.create(this.history).then((response) => {console.log(response.status);
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