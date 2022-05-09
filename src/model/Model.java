package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import contast.Contast;

public class Model {
    
    private static String usr = Contast.usr;
    private static String password = Contast.password;
    private static String driver = Contast.driver;
    private static String url = Contast.url;
    public static boolean loginAdmin(String loginAccount ,String loginPassword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("select count(*) from d_admin where a_username=? and a_password=?");
            pstmt.setString(1, loginAccount);
            pstmt.setString(2, loginPassword);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
              try {
                rs.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(pstmt != null) {
              try {
                pstmt.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(conn != null) {
              try {
                conn.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
        }
        
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean addPurchase(String content, String money, String time,String note) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("insert into purchase(content,money,time,note) values(?,?,?,?)");
            pstmt.setString(1, content );
            pstmt.setString(2, money);
            pstmt.setString(3, time);
            pstmt.setString(4, note);
            //返回受影响的行数
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          if(pstmt != null) {
            try {
              pstmt.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
          if(conn != null) {
            try {
              conn.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
        
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public static String findPurchase() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("select * from purchase order by time desc");
            rs = pstmt.executeQuery();
            while(rs.next()){
              String id = rs.getString(1);
              String content = rs.getString(2);
              String money = rs.getString(3);
              String time = rs.getString(4);
              String note = rs.getString(5);
              Map<String,String> map = new HashMap<String,String>();
              map.put("id",id);
              map.put("content",content);
              map.put("money",money);
              map.put("time",time);
              map.put("note",note);
              list.add(map);
              }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
              try {
                rs.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(pstmt != null) {
              try {
                pstmt.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(conn != null) {
              try {
                conn.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
        }
        Gson gson = new Gson();
        String listToJsonString = gson.toJson(list);
        return listToJsonString;
    }
    
    public static boolean altPurchase(String id,String content,String money,String time,String note) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("update purchase set content = ?,money = ?,time = ?,note = ? where id = ?");
            pstmt.setString(1, content);
            pstmt.setString(2, money);
            pstmt.setString(3, time);
            pstmt.setString(4, note);
            pstmt.setString(5, id);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
              try {
                rs.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(pstmt != null) {
              try {
                pstmt.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(conn != null) {
              try {
                conn.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
        }
        
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    
   public static boolean deletePurchase(String id) {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("delete from purchase where id = ?");
            pstmt.setString(1, id);
            //返回受影响的行数
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          if(pstmt != null) {
            try {
              pstmt.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
          if(conn != null) {
            try {
              conn.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
        
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    public static String login(String loginAccount ,String loginPassword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        String sta = "";
        List<Map<String, String>> list = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("select count(*),statue from user where username=? and password=?");
            pstmt.setString(1, loginAccount);
            pstmt.setString(2, loginPassword);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
                sta = rs.getString(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
              try {
                rs.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(pstmt != null) {
              try {
                pstmt.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(conn != null) {
              try {
                conn.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
        }
        
        if (count == 1) {
        	Map<String,String> map = new HashMap<String,String>();
            map.put("result","true");
            map.put("sta",sta);
            list.add(map);
        } else {
        	Map<String,String> map = new HashMap<String,String>();
        	map.put("result","false");
            map.put("sta",sta);
            list.add(map);
        }
        Gson gson = new Gson();
        String listToJsonString = gson.toJson(list);
        return listToJsonString;
    }
    
    /**
     * 注册操作
     * 注册前查询账号是否存在
     * 存在则注册失败
     */
    public static boolean register(String registerAccount ,String registerPassword,String registerName,String registerStatue) {
        //如果数据库存在该用户,则注册失败
        if (Model.login(registerAccount ,registerPassword).contains("true")) {
            return false;
        }
        System.out.println(Model.login(registerAccount ,registerPassword));
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("insert into user values(?,?,?,?,?)");
            pstmt.setString(1, registerAccount );
            pstmt.setString(2, registerPassword);
            pstmt.setString(3, registerName);
            pstmt.setString(4, registerStatue);
            pstmt.setString(5, "0");
            //返回受影响的行数
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          if(pstmt != null) {
            try {
              pstmt.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
          if(conn != null) {
            try {
              conn.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
        
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean altUser(String username,String passwords,String name,String statue) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("update user set password = ?,name=?,statue=? where username = ?");
            pstmt.setString(1, passwords);
            pstmt.setString(2, name);
            pstmt.setString(3, statue);
            pstmt.setString(4, username);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
              try {
                rs.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(pstmt != null) {
              try {
                pstmt.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(conn != null) {
              try {
                conn.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
        }
        
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    public static String findUser() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("select * from user");
            rs = pstmt.executeQuery();
            while(rs.next()){
              String username = rs.getString(1);
              String password = rs.getString(2);
              String name = rs.getString(3);
              String statue = rs.getString(4);
              Map<String,String> map = new HashMap<String,String>();
              map.put("username",username);
              map.put("password",password);
              map.put("name",name);
              map.put("statue",statue);
              list.add(map);
              }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
              try {
                rs.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(pstmt != null) {
              try {
                pstmt.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            if(conn != null) {
              try {
                conn.close();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
        }
        Gson gson = new Gson();
        String listToJsonString = gson.toJson(list);
        return listToJsonString;
    }
    
 public static boolean deleteUser(String username) {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,usr,password);
            pstmt = (PreparedStatement) conn.prepareStatement("delete from user where username = ?");
            pstmt.setString(1, username);
            //返回受影响的行数
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          if(pstmt != null) {
            try {
              pstmt.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
          if(conn != null) {
            try {
              conn.close();
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
        
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
 
 public static boolean addRoom(String phone, String number, String time,String note) {
     Connection conn = null;
     PreparedStatement pstmt = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("insert into room(phone,number,time,note) values(?,?,?,?)");
         pstmt.setString(1, phone);
         pstmt.setString(2, number);
         pstmt.setString(3, time);
         pstmt.setString(4, note);
         //返回受影响的行数
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
       if(pstmt != null) {
         try {
           pstmt.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
       if(conn != null) {
         try {
           conn.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
 
 
 public static String findRoom() {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     List<Map<String,String>> list = new ArrayList<Map<String,String>>();
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("select * from room order by time desc");
         rs = pstmt.executeQuery();
         while(rs.next()){
           String id = rs.getString(1);
           String phone = rs.getString(2);
           String number = rs.getString(3);
           String time = rs.getString(4);
           String note = rs.getString(5);
           Map<String,String> map = new HashMap<String,String>();
           map.put("id",id);
           map.put("phone",phone);
           map.put("number",number);
           map.put("time",time);
           map.put("note",note);
           list.add(map);
           }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if(rs != null) {
           try {
             rs.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(pstmt != null) {
           try {
             pstmt.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(conn != null) {
           try {
             conn.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
     }
     Gson gson = new Gson();
     String listToJsonString = gson.toJson(list);
     return listToJsonString;
 }
 
 public static boolean altRoom(String id,String phone,String number,String time,String note) {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("update room set phone = ?,number = ?,time = ?,note = ? where id = ?");
         pstmt.setString(1, phone);
         pstmt.setString(2, number);
         pstmt.setString(3, time);
         pstmt.setString(4, note);
         pstmt.setString(5, id);
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if(rs != null) {
           try {
             rs.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(pstmt != null) {
           try {
             pstmt.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(conn != null) {
           try {
             conn.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
 
 public static boolean addComment(String commentId,String username,String comment) {
     
     Connection conn = null;
     PreparedStatement pstmt = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("insert into comment(commentId,username,comment) values(?,?,?)");
         pstmt.setString(1, commentId);
         pstmt.setString(2, username);
         pstmt.setString(3, comment);
         //返回受影响的行数
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
       if(pstmt != null) {
         try {
           pstmt.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
       if(conn != null) {
         try {
           conn.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
 
 public static String findComment() {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     List<Map<String,String>> list = new ArrayList<Map<String,String>>();
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("select * from comment");
         rs = pstmt.executeQuery();
         while(rs.next()){
           int id = rs.getInt(1);
           String commentId = rs.getString(2);
           String username = rs.getString(3);
           String comment = rs.getString(4);
           Map<String,String> map = new HashMap<String,String>();
           map.put("id",String.valueOf(id));
           map.put("commentId",commentId);
           map.put("username",username);
           map.put("comment",comment);
           list.add(map);
           }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if(rs != null) {
           try {
             rs.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(pstmt != null) {
           try {
             pstmt.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(conn != null) {
           try {
             conn.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
     }
     Gson gson = new Gson();
     String listToJsonString = gson.toJson(list);
     return listToJsonString;
 }
 
 public static boolean addCommunication(String username,String content,String time) {
     
     Connection conn = null;
     PreparedStatement pstmt = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("insert into communication(username,content,time) values(?,?,?)");
         pstmt.setString(1, username);
         pstmt.setString(2, content);
         pstmt.setString(3, time);
         //返回受影响的行数
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
       if(pstmt != null) {
         try {
           pstmt.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
       if(conn != null) {
         try {
           conn.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
 
 public static String findCommunication() {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     List<Map<String,String>> list = new ArrayList<Map<String,String>>();
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("select * from communication order by id desc");
         rs = pstmt.executeQuery();
         while(rs.next()){
           int id = rs.getInt(1);
           String username = rs.getString(2);
           String content = rs.getString(3);
           String time = rs.getString(4);
           Map<String,String> map = new HashMap<String,String>();
           map.put("id",String.valueOf(id));
           map.put("username",username);
           map.put("content",content);
           map.put("time",time);
           list.add(map);
           }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if(rs != null) {
           try {
             rs.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(pstmt != null) {
           try {
             pstmt.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(conn != null) {
           try {
             conn.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
     }
     Gson gson = new Gson();
     String listToJsonString = gson.toJson(list);
     return listToJsonString;
 }
public static boolean deleteRoom(String id) {
     
     Connection conn = null;
     PreparedStatement pstmt = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("delete from room where id = ?");
         pstmt.setString(1, id);
         //返回受影响的行数
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
       if(pstmt != null) {
         try {
           pstmt.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
       if(conn != null) {
         try {
           conn.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
    
 public static boolean addDish(String name, String price, String types,String note,String newprice) {
     Connection conn = null;
     PreparedStatement pstmt = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("insert into dish(name,price,types,note,newprice) values(?,?,?,?,?)");
         pstmt.setString(1, name );
         pstmt.setString(2, price);
         pstmt.setString(3, types);
         pstmt.setString(4, note);
         pstmt.setString(5, newprice);
         //返回受影响的行数
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
       if(pstmt != null) {
         try {
           pstmt.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
       if(conn != null) {
         try {
           conn.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
 
 
 public static String findDish() {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     List<Map<String,String>> list = new ArrayList<Map<String,String>>();
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("select * from dish order by id desc");
         rs = pstmt.executeQuery();
         while(rs.next()){
           String id = rs.getString(1);
           String name = rs.getString(2);
           String price = rs.getString(3);
           String types = rs.getString(4);
           String note = rs.getString(5);
           String newprice = rs.getString(6);
           Map<String,String> map = new HashMap<String,String>();
           map.put("id",id);
           map.put("name",name);
           map.put("price",price);
           map.put("types",types);
           map.put("note",note);
           map.put("newprice",newprice);
           list.add(map);
           }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if(rs != null) {
           try {
             rs.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(pstmt != null) {
           try {
             pstmt.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(conn != null) {
           try {
             conn.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
     }
     Gson gson = new Gson();
     String listToJsonString = gson.toJson(list);
     return listToJsonString;
 }
 
 public static boolean altDish(String id,String name,String price,String types,String note,String newprice) {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("update dish set name = ?,price = ?,types = ?,note = ?,newprice=? where id = ?");
         pstmt.setString(1, name);
         pstmt.setString(2, price);
         pstmt.setString(3, types);
         pstmt.setString(4, note);
         pstmt.setString(5, newprice);
         pstmt.setString(6, id);
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if(rs != null) {
           try {
             rs.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(pstmt != null) {
           try {
             pstmt.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
         if(conn != null) {
           try {
             conn.close();
           } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
         }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
 
 
public static boolean deleteDish(String id) {
     
     Connection conn = null;
     PreparedStatement pstmt = null;
     int count = 0;
     try {
         Class.forName(driver);
         conn = (Connection) DriverManager.getConnection(url,usr,password);
         pstmt = (PreparedStatement) conn.prepareStatement("delete from dish where id = ?");
         pstmt.setString(1, id);
         //返回受影响的行数
         count = pstmt.executeUpdate();
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
       if(pstmt != null) {
         try {
           pstmt.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
       if(conn != null) {
         try {
           conn.close();
         } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
       }
     }
     
     if (count == 1) {
         return true;
     } else {
         return false;
     }
 }
    

public static boolean deleteDesk(String id) {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("delete from desk where id = ?");
        pstmt.setString(1, id);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}
    
public static boolean addDesk(String number, String total,String username) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("insert into desk(number,total,username) values(?,?,?)");
        pstmt.setString(1, number);
        pstmt.setString(2, total);
        pstmt.setString(3, username);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}

public static boolean altBank(String username,String money) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("update user set money = ? where username = ?");
        pstmt.setString(1, money);
        pstmt.setString(2, username);
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if(rs != null) {
          try {
            rs.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(pstmt != null) {
          try {
            pstmt.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}

public static boolean addBank(String username, String money) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("insert into bank(username,money) values(?,?)");
        pstmt.setString(1, username);
        pstmt.setString(2, money);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}

public static String findBank(String username) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("select * from user where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        while(rs.next()){
          String money = rs.getString(5);
          Map<String,String> map = new HashMap<String,String>();
          map.put("money",money);
          map.put("username",username);
          list.add(map);
          }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if(rs != null) {
          try {
            rs.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(pstmt != null) {
          try {
            pstmt.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
    }
    Gson gson = new Gson();
    String listToJsonString = gson.toJson(list);
    return listToJsonString;
}

public static String findDesk() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("select * from desk order by id desc");
        rs = pstmt.executeQuery();
        while(rs.next()){
          String id = rs.getString(1);
          String number = rs.getString(2);
          String total = rs.getString(3);
          String username = rs.getString(4);
          Map<String,String> map = new HashMap<String,String>();
          map.put("id",id);
          map.put("number",number);
          map.put("total",total);
          map.put("username",username);
          list.add(map);
          }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if(rs != null) {
          try {
            rs.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(pstmt != null) {
          try {
            pstmt.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
    }
    Gson gson = new Gson();
    String listToJsonString = gson.toJson(list);
    return listToJsonString;
}
    
public static boolean addCart(String did, String cid,String name,String price,String types,String note,String username,String newprice) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("insert into cart(did,cid,name,price,types,note,newprice,username) values(?,?,?,?,?,?,?,?)");
        pstmt.setString(1, did);
        pstmt.setString(2, cid);
        pstmt.setString(3, name);
        pstmt.setString(4, price);
        pstmt.setString(5, types);
        pstmt.setString(6, note);
        pstmt.setString(7, newprice);
        pstmt.setString(8, username);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}


public static String findCart() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("select * from cart");
        rs = pstmt.executeQuery();
        while(rs.next()){
          String id = rs.getString(1);
          String did = rs.getString(2);
          String cid = rs.getString(3);
          String name = rs.getString(4);
          String price = rs.getString(5);
          String types = rs.getString(6);
          String note = rs.getString(7);
          String username = rs.getString(8);
          String newprice = rs.getString(9);
          Map<String,String> map = new HashMap<String,String>();
          map.put("id",id);
          map.put("did",did);
          map.put("cid",cid);
          map.put("name",name);
          map.put("price",price);
          map.put("types",types);
          map.put("note",note);
          map.put("username",username);
          map.put("newprice",newprice);
          list.add(map);
          }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if(rs != null) {
          try {
            rs.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(pstmt != null) {
          try {
            pstmt.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
    }
    Gson gson = new Gson();
    String listToJsonString = gson.toJson(list);
    return listToJsonString;
}
    

public static boolean deleteAllCart(String did) {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("delete from cart where did = ?");
        pstmt.setString(1, did);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}
public static boolean deleteAllDesk(String number) {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("delete from desk where number = ?");
        pstmt.setString(1, number);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}

public static boolean deleteCart(String id) {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("delete from cart where id = ?");
        pstmt.setString(1, id);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}
    
    
public static boolean addRecord(String name, String price,String number,String time) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("insert into record(name,price,number,time) values(?,?,?,?)");
        pstmt.setString(1, name );
        pstmt.setString(2, price);
        pstmt.setString(3, number);
        pstmt.setString(4, time);
        //返回受影响的行数
        count = pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
      if(pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    if (count == 1) {
        return true;
    } else {
        return false;
    }
}

public static String findRecord() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    int count = 0;
    try {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,usr,password);
        pstmt = (PreparedStatement) conn.prepareStatement("select * from record");
        rs = pstmt.executeQuery();
        while(rs.next()){
          String id = rs.getString(1);
          String name = rs.getString(2);
          String price = rs.getString(3);
          String number = rs.getString(4);
          String time = rs.getString(5);
          Map<String,String> map = new HashMap<String,String>();
          map.put("id",id);
          map.put("name",name);
          map.put("price",price);
          map.put("number",number);
          map.put("time",time);
          list.add(map);
          }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if(rs != null) {
          try {
            rs.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(pstmt != null) {
          try {
            pstmt.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        if(conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
    }
    Gson gson = new Gson();
    String listToJsonString = gson.toJson(list);
    return listToJsonString;
}
}

    

 