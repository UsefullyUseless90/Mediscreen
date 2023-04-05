<template>
  <div class="historyPage">
      <div class="updateHistory">
        <h1>Patient's informations</h1>
      </div>
      <form action="app.js" method="put">
        <div class="formUpdate">
          <label class="com" for="Commentary" > Commentary </label>
          <input type="text" class="commentary" id="commentary" v-model="history.commentary"/>
        </div>
        <div class="containsB">
        <button class="ubtn" @click="update()">Update Informations</button>
        </div>
      </form>
  </div>
      
</template>
<script>
import historyDataService from "../service/historyDataService";
import patientDataService from "../service/patientDataService";

export default {
  name: "info-history",
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
      patientDataService.getPatientId(this.$data.history.patientId).then((response) => {this.$data.patientDAO = {...response.data};
      })
      this.$router.push({name:'history', params: {id: this.$data.history.patientId}})
      },
    }
}
</script>
<style>
.com{
  font-weight: bolder;
  font-size: x-large;
  margin-left: -10em;
}
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
    margin-left: -1em;
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
.commentary{
  height: 10em;
  margin-left: 5em;
}
</style>
