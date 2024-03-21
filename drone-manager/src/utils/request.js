
import axios from "axios";
import ElementUI from "element-ui";


const request =axios.create({
    baseURL:'http://localhost:9090',
    timeout:5000
})


request.interceptors.request.use(config =>{
    config.headers['Content-Type']='application/json;charset=utf-8';

     let user=localStorage.getItem("user") ?JSON.parse(localStorage.getItem("user")):null
     if(user){
        //设置请求头
       config.headers['token']=user.token
     }
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
        //当权限验证不通过给出提示
        if(res.code==='401'){
            ElementUI.Message(
                {
                    message:res.msg,
                    type:'error'
                }
            );
        }

        return res;
    },
    error =>{
        console.log('err'+error)
        return Promise.reject(error)
    }

)
export default request