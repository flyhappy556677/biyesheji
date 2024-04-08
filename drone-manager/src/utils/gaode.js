/* JSAPI key搭配静态安全密钥以明文设置（不安全，建议只在开发环境使用) */
import AMapLoader from '@amap/amap-jsapi-loader';

const securityJsCode = '59a5db0a61cd8b39aac0e570e8a0de47'; // 您申请的安全密钥
const key = 'f34b8d5f4cbdb16a9f986dcb3b35da8a'; // 申请好的Web端开发者Key，首次调用 load 时必填
const version = '2.0'; // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
const adress = 'https://webapi.amap.com/loader.js'; // 代理服务器地址

window._AMapSecurityConfig = {
    securityJsCode // 您申请的安全密钥
};

/**
 * 载入地图
 * @param {*} plugins 地图配置项
 * @returns promise
 */
const mapLoader = plugins =>
    AMapLoader.load({
        key, // 申请好的Web端开发者Key，首次调用 load 时必填
        version, // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins // 需要使用的的插件列表，如比例尺'AMap.Scale'等
    });

// 动态创建script标签，引入代理服务器
const script = document.createElement('script');
script.type = 'text/javascript';
script.src = adress;
document.body.appendChild(script);

// 导出用来载入地图的函数
export default mapLoader;
