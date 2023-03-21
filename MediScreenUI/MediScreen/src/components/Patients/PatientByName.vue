<template>
  <div class="infos">
       <strong><p>Name</p></strong>
        <input
          type="text"
          id="name"
          name="name"
          v-model="name"
        />
      <strong><p>Firstname</p></strong>  
        <input
          type="text"
          id="firstName"
          name="firstName"
          v-model="firstName"/>
      <button type="button" v-on:click="lookOutForPatient()">Search</button>
      <p>{{ patient}}</p>
    </div>
</template>
<script>



export default {
  name: "patient-item",
  data() {
    return {
        name: "", 
        firstName: "",
    }
  },
}
</script>