<template>
  <div>
    <table class="center">
      <thead>
        <tr>
          <th scope="col">Patient's id</th>
          <th scope="col">Name</th>
          <th scope="col">Firstname</th>
        </tr>
      </thead>
    </table>
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
      }
    }
  },
}
</script>

<style>
.center {
  background-color: rgb(50, 102, 151);
}


</style>