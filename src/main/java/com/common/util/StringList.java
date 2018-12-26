package com.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**     
 * 类名称：StringList    
 * 类描述：   字符串数组操作工具类
 */
public class StringList
{
    private ArrayList lst = new ArrayList();
    //  分隔符/分割符
    private String delimiter = ";";

    public StringList()
    {
        this.lst = new ArrayList();
    }

    public StringList(String[] strs)
    {
        if (strs != null)
        {
            for (int i = 0; i < strs.length; i++)
            {
                lst.add(strs[i]);
            }
        }
    }

    public StringList(ArrayList plst)
    {
        this.lst = plst;
    }

    public int size()
    {
        return lst.size();
    }

    public void add(String s)
    {
        lst.add(s);
    }

    public void remove(String s)
    {
        lst.remove(s);
    }

    /**
     * 将lst转换为字符串数组 
     * @return
     */
    public String[] toStringArr()
    {
        String[] str = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++)
        {
            str[i] = lst.get(i).toString();
        }
        return str;
    }

    /**
     * 获得指定位置的字符串
     * @param i
     * @return
     */
    public String get(int i)
    {
        return lst.get(i).toString();
    }

    public void set(int i, String value)
    {
        lst.set(i, value);
    }

    /**
     *  获得指定字符串的位置若找不到则返回-1
     * @param str
     * @return
     */
    public int indexOf(String str)
    {
        return 0;
    }

    public void clear()
    {
        lst.clear();
    }

    /**
     *free对象
     *
     */
    public void free()
    {
    }

    /**
     * 是否包含指定的字符串
     * 
     * @param str
     * @return
     */
    public boolean contains(String str)
    {
        if (lst.contains(str))
            return true;
        return false;
    }

    /**
     * 从输入流中读取每行
     * 
     * @param input
     *            待读取的输入流
     * @throws IOException
     *             流读取异常
     */
    public void loadFromStream(InputStream input) throws IOException
    {
        BufferedReader br = null;
        String line = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(input));
            this.lst.clear();
            while (null != (line = br.readLine()))
            {
                this.lst.add(line);
            }
        }
        finally
        {
            if (null != br)
            {
                br.close();
            }
        }
    }

    /**
     * 从文件读取每行内容保存到StringList
     * 
     * @param sFilePath
     *            文件路径
     * @throws IOException
     *             IOException
     */
    public void loadFromFile(String sFilePath) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(sFilePath);
            this.loadFromStream(fis);
        }
        finally
        {
            if (null != fis)
            {
                fis.close();
            }
        }
    }

    /**
     * 保存倒输出流，每一个字符串在输出流中写一行
     * 
     * @param output
     *            待输出流
     * @throws IOException
     *             输入异常
     */
    public void saveToStream(OutputStream output) throws IOException
    {
        PrintWriter ps = null;
        try
        {
            ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)));
            for (int i = 0; i < this.lst.size(); i++)
            {
                ps.println((String) this.lst.get(i));
            }
        }
        finally
        {
            if (null != ps)
            {
                ps.flush();
                ps.close();
            }
        }
    }

    /**
     * 保存所有字符串到文件
     * 
     * @param sFilePath
     *            待存文件
     * @throws IOException
     *             输出异常
     */
    public void saveToFile(String sFilePath) throws IOException
    {
        FileOutputStream fou = null;
        try
        {
            fou = new FileOutputStream(sFilePath);
            this.saveToStream(fou);
        }
        finally
        {
            if (null != fou)
            {
                fou.flush();
                fou.close();
            }
        }
    }

    /**
     * 返回一个新的StringList实例,该实例只包含本实例的distinct字符串
     * 
     * @return 一个StringList实例
     */
    public StringList getDistinctStrings()
    {
        TreeSet tset = new TreeSet();
        try
        {
            tset.addAll(this.lst);
            StringList ret = new StringList();
            ret.lst.addAll(tset);
            return ret;
        }
        finally
        {
            tset.clear();
        }
    }

    /**
     * 升序排序。排序过程借助TreeSet完成。
     */
    public void sort()
    {
        //红黑树集合，借助该集合进行排序
        TreeSet tset = new TreeSet(new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                String s1 = (String) o1;
                String s2 = (String) o2;
                int ret = s1.compareTo(s2);
                //相等视为小于，这是为了避免TreeSet忽略相等的元素
                if (ret == 0)
                {
                    ret = -1;
                }
                return ret;
            }
        });
        try
        {
            tset.addAll(this.lst);
            this.lst.clear();
            this.lst.addAll(tset);
        }
        finally
        {
            tset.clear();
        }
    }

    /**
     * 将各个字符串之间用分隔字符串连接后返回
     * 
     * @return 一个字符串
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < this.lst.size() - 1; i++)
        {
            sb.append(this.lst.get(i).toString());
            sb.append(this.delimiter);
        }
        if (this.lst.size() > 0)
        {
            sb.append(this.lst.get(this.lst.size() - 1));
        }
        sb.append("]");
        return sb.toString();
    }
}
