using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

namespace UsefulC_SHARP {
    public class Program {
        public static void Main(string[] args) {

            MyPojo b = new MyPojo();
            b.Name = "Hello World";

            XmlSerializer s = new XmlSerializer(b.GetType());

            StringWriter sww = new StringWriter();
            XmlWriter writer = XmlWriter.Create(sww);
            s.Serialize(writer, b);

            var xml = sww.ToString();
            Console.WriteLine(xml);
            
            Console.ReadLine();
        }
    }
}
