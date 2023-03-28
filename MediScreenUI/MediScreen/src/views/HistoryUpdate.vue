<template>
  <div class="historyPage">
      <div class="updateHistory">
        <h1>Patient's informations</h1>
        <td>{{ history.historyId }}</td>
        <td>{{ history.patientId }}</td>
      </div>
      <div class="card-body"></div>

      <form action="app.js" method="put">
        
        <div class="form-group">
          <label for="PhoneNumber" > Commentary </label>
          <input type="text" class="form-control" id="commentary" v-model="history.commentary"/>
        </div>
        <div class="containsB">
        <button class="ubtn" @click="update()">Update Informations</button>
        </div>
      </form>
  </div>
      
</template>
<script>
import historyDataService from "../service/historyDataService";


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
        patientId:0,
        historyId:0,
        commentary:""
      },
    }
  },
  mounted(){
      console.log(this.$route.params.id);
        historyDataService.getHistoryId(this.$route.params.id).then((response) => {this.history = {...response.data};
      })
  },
  methods: {
  update() {
      historyDataService.update(this.history).then((response) => {console.log(response.status);
      })
      this.$router.push({name:'history', params: {id: patientDAO.idPatient}})
      },
    }
}
</script>
<style>
.historyPage{
  position:absolute;
  display: block;
  margin-top: 10em;
}
.historyPage h1{
  color: rgb(50, 102, 151);
  font-weight: bold;
  font-size: x-large;
}
.ubtn{
    margin-left: 1em;
    font-weight: bolder;
    position: absolute;
    background-color: rgb(50, 102, 151);
    color: #fff;
    border: 1px rgb(50, 102, 151);
    border-radius: 20px / 20px;
    height: 2.5em;
    margin-top: 1em;
    width: 20em;
}
</style>
