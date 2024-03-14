
import axios from "axios";


const request =axios.create({
    baseURL:'http://localhost:9090',
    timeout:5000
})


request.interceptors.request.use(config =>{
    config.headers['Content-Type']='application/json;charset=utf-8';

    // const userJson=Cookies.get('user')
    // if(userJson){
    //     //设置请求头
    //     config.headers['token']=JSON.parse(userJson).token
    // }


    return config
},
    error =>{
        return Promise.reject(error)
    }
);

request.interceptors.response.use(
    response => {
        let res =response.data;
        if(response.config.responseType === 'blob'){
            return res
        }
        //兼容服务端返回字符串数据
        if(typeof res ==='string'){
            res =res ? JSON.parse(res):res
        }

        return res;
    },
    error =>{
        console.log('err'+error)
        return Promise.reject(error)
    }

)
export default request