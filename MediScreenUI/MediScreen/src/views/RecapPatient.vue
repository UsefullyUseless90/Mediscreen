<template>
  <div class="AllPatients">
    <div class="recap">
        <h1>Patients</h1>
    </div>
      <AllPatient/>
    </div>


</template>

<script>
import AllPatient from '@/components/Patients/AllPatient.vue';
import patientTable from '@/components/patients/patientTable.vue';


export default{
    name: "patients-item",
    
    components: {
      patientDAO:{
        name:"",
        firstName:"",
        idPatient:""
      },
    patientTable,
    AllPatient
},
methods:{
    lookOutForPatient(){
    patientDataService.getPatient(this.name, this.firstName).then((response) => {this.patientDAO = {...response.data}}); 
      },
  }
};
</script>
<style>

.infos{
  margin-top: 5em;
  display: flex;
  justify-content: flex-start;
  justify-content: space-between;
  height: 4em;
  margin-bottom: 2em;
}
.btn1{
  margin-left: 0em;
  background-color: rgb(50, 102, 151);
    color: #fff;
    height: 50px;
    border: 1px rgb(50, 102, 151);;
    border-radius: 20px / 20px;
    font-weight: bold;
    font-size: x-large;
    margin-top: 0.5em;
    width: 300px;
    height: 50px;
    
}
input{
 margin-top: 1em;
 width: 300px;
 border-radius: 20px / 20px;
}

.AllPatients{
  position: absolute;
  margin-top: 2.5em;
}
.recap{
    font-weight: bolder;
    margin-top: 10em;
    color: rgb(50, 102, 151);
}
table{
  color: white;
}
</style>