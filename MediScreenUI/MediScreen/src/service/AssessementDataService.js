import axios from 'axios'

class AssessementDataService{

getAssessById(idPatient){
    return axios.get("http://localhost:8080/assess/id?patientId=" + idPatient)
  }
  getAssessByFullName(name, firstName){
    return axios.get("http://localhost:8080/assess/familyName?name="+ name + "&firstName=" + firstName)
  }
  
}
export default new AssessementDataService();
