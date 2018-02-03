from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import json

class S(BaseHTTPRequestHandler):
    body_regions_dict = {
        "body_regions" : [
        {
            "id" : 0,
            "name" : "front_hip"
        }, 
        {
            "id" : 1,
            "name" : "front_head"
        },
        {
            "id" : 2,
            "name" : "front_feet"
        }]
    }

    specific_regions = {
        0 : [
            {
                "id" : 0,
                "name" : "deep_hip"
            },
            {
                "id" : 1,
                "name" : "anterolateral"
            },
            {
                "id" : 2,
                "name" : "posterior"
            },
            {
                "id" : 3,
                "name" : "anterior"
            },
            {
                "id" : 4,
                "name" : "medial"
            },
            {
                "id" : 5,
                "name" : "lateral"
            }]
    }

    causes = {
        0 : {
            2 : [
                {
                    "id" : 0,
                    "name" : "Gluteus Maximus Bursitis",
                    "classification" : "default"
                },
                {
                    "id" : 1,
                    "name" : "Coccydynia",
                    "classification" : "default"
                },
                {
                    "id" : 2,
                    "name" : "Ischial Tuberosity Avulsion Fracture",
                    "classification" : "children"
                }],
            4 : [
                {
                    "id" : 0,
                    "name" : "Adductor Tendinitis",
                    "classification" : "default"
                },
                {
                    "id" : 1,
                    "name" : "Ramus Avulsion Fracture",
                    "classification" : "children"
                },
                {
                    "id" : 2,
                    "name" : "Testicular Torsion",
                    "classification" : "extreme"
                }]
        }
    }


    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-Type', 'application/json')
        self.end_headers()

    def do_GET(self):
        if self.path == "/PocketDoc/body_regions":
            self._set_headers()
            out = json.dumps(self.body_regions_dict)
            self.wfile.write(out)
        else:
            self.send_error(404)

    def do_HEAD(self):
        self._set_headers()

    def do_POST(self):
        if self.path == "/PocketDoc/body_regions":
            self.do_body_regions_post()
        elif self.path == "/PocketDoc/specific_regions":
            self.do_specific_regions_post()
        elif self.path == "/PocketDoc/causes":
            self.do_causes_post()
        else:
            self.send_error(404)

    def do_body_regions_post(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        post_data = json.loads(post_data)
        body_region_id = post_data["id"]
        data = dict(self.body_regions_dict["body_regions"][body_region_id])
        lst = self.specific_regions[body_region_id]
        data["specific_regions"] = lst
        self._set_headers()
        self.wfile.write(json.dumps(data))

    def do_specific_regions_post(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        post_data = json.loads(post_data)
        body_region_id = post_data["body_region_id"]
        specific_region_id = post_data["id"]
        data = dict(self.specific_regions[body_region_id][specific_region_id])
        lst = self.causes[body_region_id][specific_region_id]
        data["causes"] = lst
        self._set_headers()
        self.wfile.write(json.dumps(data))

    def do_causes_post(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        post_data = json.loads(post_data)
        body_region_id = post_data["body_region_id"]
        specific_region_id = post_data["specific_region_id"]
        cause_id = post_data["id"]
        data = dict(self.causes[body_region_id][specific_region_id][cause_id])
        self._set_headers()
        self.wfile.write(json.dumps(data))

def run(server_class=HTTPServer, handler_class=S, port=8080):
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)
    print 'Starting httpd...'
    httpd.serve_forever()

if __name__ == "__main__":
    from sys import argv

    if len(argv) == 2:
        run(port=int(argv[1]))
    else:
        run()
