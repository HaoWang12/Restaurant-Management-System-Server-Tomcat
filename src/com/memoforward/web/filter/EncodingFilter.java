package com.memoforward.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        chain.doFilter(new EnhancedRequest(request), response);
    }

    public void init(FilterConfig config) throws ServletException {}

    class EnhancedRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;
        boolean flag = false;

        public EnhancedRequest(HttpServletRequest req){
            super(req);
            this.request = req;
        }

        @Override
        public String getParameter(String name) {
            if(name==null || name.trim().length()==0){
                return null;
            }
            String[] values = getParameterValues(name);
            if(values==null || values.length==0){
                return null;
            }

            return values[0];
        }

        @Override
        /**
         * hobby=[eat,drink]
         */
        public String[] getParameterValues(String name) {
            if(name==null || name.trim().length()==0){
                return null;
            }
            Map<String, String[]> map = getParameterMap();
            if(map==null || map.size()==0){
                return null;
            }

            return map.get(name);
        }

        @Override
        /**
         * map{ username=[tom],password=[123],hobby=[eat,drink]}
         */
        public Map<String,String[]> getParameterMap() {

            /**
             * ????????????????????????
             * ??????post  request.setchar...(utf-8)
             * ??????get ???map?????????????????????????????????
             */
            String method = request.getMethod();
            if("post".equalsIgnoreCase(method)){
                try {
                    request.setCharacterEncoding("utf-8");
                    return request.getParameterMap();
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if("get".equalsIgnoreCase(method)){
                Map<String,String[]> map = request.getParameterMap();
                if(flag){
                    for (String key:map.keySet()) {
                        String[] arr = map.get(key);
                        //??????????????????
                        for(int i=0;i<arr.length;i++){
                            //??????
                            try {
                                arr[i]=new String(arr[i].getBytes("iso-8859-1"),"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    flag=false;
                }
                //????????????map ??????value???????????????????????????

                return map;
            }

            return super.getParameterMap();
        }

    }

}
