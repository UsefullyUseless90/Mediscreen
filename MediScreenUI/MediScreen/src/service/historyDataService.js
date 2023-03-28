import axios from 'axios'

class HistoryDataService{

  getAll(){
    return axios.get("http://localhost:8082/patHistory/allHistories")
  }
  getHistoriesId(idHistory){
    return axios.get("http://localhost:8082/patHistory/historiesId?id=" + idHistory)
  }
  getHistoryId(idHistory){
    return axios.get("http://localhost:8082/patHistory/historyId?id=" + idHistory)
  }
  create(data) {
    return axios.post("http://localhost:8082/patHistory/add", data);
  }
  update(data){
    return axios.put("http://localhost:8082/patHistory/edit", data);
  }
}

export default new HistoryDataService();