<template>
  <div class="historyPage">
      <div class="card card-primary">
        <h1>Patient's informations</h1>
      </div>
      <div class="card-body"></div>

      <form action="app.js" method="put">
        <div class="form-group">
          <label for="LastName"> Last name </label>
          <input type="text" class="form-control" id="name" v-model="history.name"/>
        </div>
        <div class="form-group">
          <label for="FirstName"> First name </label>
          <input type="text" class="form-control" id="firstName" v-model="history.firstName" />
        </div>
        <div class="form-group">
          <label for="PostalAddress" > Date of Interview </label>
          <input type="text" class="form-control" id="dateOfInterview" v-model="history.dateOfInterview"/>
        </div>
        <div class="form-group">
          <label for="PhoneNumber" > Commentary </label>
          <input type="text" class="form-control" id="commentary" v-model="history.commentary"/>
        </div>
        <div class="containsB">
          <router-link to="/patientsHistory/update/:id" class="nav-link"><button class="ubtn" @click="update()">Update Informations</button></router-link>
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
      history:{
        name: "", 
        firstName: "",
        commentary:"",
        dateOfInterview:""
      },
    }
  },
  mounted(){
    console.log(this.$route.params.id);
    historyDataService.update(this.$route.params.id).then((response) => {console.log(response.status);
    })
  },
  methods: {
  update() {
      historyDataService.update(this.history).then((response) => {console.log(response.status);
      })
      .catch((error) => console.error({error})); 
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
