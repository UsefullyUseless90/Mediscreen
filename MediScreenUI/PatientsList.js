<template>
  <div class="table">
    <table>
      <tbody v-for="(connexion, index) in connexions" :key="index">
        <tr>
          <td>{{ connexion.idUtilisateur}}</td>
          <td>{{ connexion.nomPrenom }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script></script>