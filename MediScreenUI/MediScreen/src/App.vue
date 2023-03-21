
<template>
  <header>
    <div class="navbar" >
      <div class="logo">
          <img src="./assets/MediScreen.jpg">
        </div>
          <router-link to="/" class="nav-link">Home</router-link>
          <router-link to="/patients" class="nav-link">Patients</router-link>
          <router-link to="/addNewPatient" class="nav-link">Add a new patient</router-link>
          <router-view></router-view>
    </div>
  </header>
</template>
<style>

a.router-link-exact-active {
color : white; 
font-weight: bolder;
margin-top: 2em;
}
a.nav-link{
  color: white;
  font-weight: bolder;
  margin-top: 2em;
}
.logo{
  margin-left: -20em;
}

.navbar{
  position: relative;
  display: flex;
  justify-content: flex-end;
  justify-content: space-evenly;
  margin-bottom: auto;
  height: 5em;
  background-color: rgb(50, 102, 151);
}
</style>
